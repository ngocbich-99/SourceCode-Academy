package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.enums.ResponseEnum;
import com.example.demo.exception.BizException;
import com.example.demo.jwt.JwtProvider;
import com.example.demo.jwt.JwtSubject;
import com.example.demo.jwt.SecurityService;
import com.example.demo.model.dto.AccountDTO;
import com.example.demo.model.dto.CourseEnrollDTO;
import com.example.demo.model.request.account.ChangePasswordRequest;
import com.example.demo.model.request.account.ChangeUserInfoRequest;
import com.example.demo.model.request.account.CreateAccountRequest;
import com.example.demo.model.request.account.UpdateAccountRequest;
import com.example.demo.model.request.auth.LoginRequest;
import com.example.demo.model.request.auth.RegisterAccountRequest;
import com.example.demo.model.response.user.LoginResponse;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CourseEnrollRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private CourseEnrollRepository courseEnrollRepository;

    @Override
    public List<AccountDTO> getListAccount() {
        return convertToListAccountDTO(accountRepository.findAll());
    }

    @Override
    public AccountDTO getAccountById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if (!account.isPresent()) {
            throw new BizException(ResponseEnum.NOT_FOUND, "Not found account");
        }
        return convertToAccountDTO(account.get());
    }

    @Override
    public AccountDTO createAcc(CreateAccountRequest accountReq) {
//        kiem tra email da ton tai chua
        Account accountExist = accountRepository.findByEmail(accountReq.getEmail());
        if (accountExist != null) {
            throw new BizException(ResponseEnum.ACCOUNT_USERNAME_EXISTED, "Email đã được sử dụng");
        }
//        if(!this.getRoleCurrentUser().equals(RoleConstant.ADMIN)){
//            throw new BizException(ResponseEnum.PERMISSIONS_DENY,"Bạn không có quyền tạo tài khoản");
//        }
        Account account = new Account();
        BeanUtils.copyProperties(accountReq, account);
        return convertToAccountDTO(accountRepository.save(account));
    }

    @Override
    public AccountDTO updateAcc(UpdateAccountRequest request) {
        Account account = findAccountById(request.getId());
        BeanUtils.copyProperties(request, account);
        return convertToAccountDTO(accountRepository.save(account));
    }

    @Override
    public void deleteAcc(Long id) {
        Account account = findAccountById(id);
        accountRepository.delete(account);
    }

    @Override
    public List<AccountDTO> getAccountActivate() {
        return convertToListAccountDTO(accountRepository.getAccountActivate());
    }

    @Override
    public List<AccountDTO> getAccountLock() {
        return convertToListAccountDTO(accountRepository.getAccountLock());
    }

    @Override
    public AccountDTO reg(RegisterAccountRequest request) {
        Account accountExist = accountRepository.findByEmail(request.getEmail());
        if (accountExist != null) {
            throw new BizException(ResponseEnum.ACCOUNT_USERNAME_EXISTED, "Email đã được sử dụng");
        }
        Account account = new Account();
        BeanUtils.copyProperties(request, account);
        if (!request.getPassword().equals(request.getReEnterPassword())) {
            throw new BizException(ResponseEnum.PASSWORD_INVALID, "Password not same ReEnterPassword");
        }
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        return this.convertToAccountDTO(accountRepository.save(account));
    }

    @Override
    public LoginResponse login(LoginRequest request) throws JsonProcessingException {
        Account account = accountRepository.findAccountByEmailOrUsernameOrPhone(request.getUsername(), request.getUsername(), request.getUsername());
        if (account == null) {
            throw new BizException(ResponseEnum.NOT_FOUND, "Not found account");
        }
        if (!passwordEncoder.matches(request.getPassword(), account.getPassword())) {
            throw new BizException(ResponseEnum.NOT_FOUND, "Username or password incorrect");
        }
        if (!account.getIsActivate()) {
            throw new BizException(ResponseEnum.ACCOUNT_BLOCKED, "Your account has been blocked. Please contact Xcademy hotline for assistance");
        }
        LoginResponse response = new LoginResponse();
        JwtSubject jwtSubject = new JwtSubject();
        BeanUtils.copyProperties(account, jwtSubject);
        BeanUtils.copyProperties(account, response);
        String accessToken = jwtProvider.generateJwtToken(jwtSubject);
        response.setAccessToken(accessToken);
        response.setTokenType("Bearer");
        TypeToken<List<CourseEnrollDTO>> typeToken = new TypeToken<List<CourseEnrollDTO>>(){};
        response.setCourseEnrolls(mapper.
                map(courseEnrollRepository.findByAccountId(response.getId()),
                typeToken.getType()));
        return response;
    }

    @Override
    public AccountDTO getCurrentUser() {
        return this.getAccountById(securityService.getCurrentUser().getId());
    }

    @Override
    public void changePassword(ChangePasswordRequest request) {
        Account account = this.findAccountById(securityService.getCurrentUser().getId());
        if (!request.getNewPassword().equals(request.getReNewPassword())) {
            throw new BizException(ResponseEnum.PASSWORD_INVALID, "Mật khẩu mới và nhập lại mật khẩu mới phải giống nhau");
        }
        if (!passwordEncoder.matches(request.getOldPassword(), account.getPassword())) {
            throw new BizException(ResponseEnum.OLD_PASSWORD_INCORRECT, "Old password incorrect");
        }
        account.setPassword(passwordEncoder.encode(request.getNewPassword()));
        accountRepository.save(account);
    }

    /**
     * change User Info
     *
     * @param request User info change
     * @return AccountDTO
     */
    @Override
    public AccountDTO changeUserInfo(ChangeUserInfoRequest request) {
        Account account = findAccountById(securityService.getCurrentUser().getId());
        BeanUtils.copyProperties(request, account);
        return convertToAccountDTO(accountRepository.save(account));
    }

    /**
     * Convert Account entity to AccountDTO
     *
     * @param account entity
     * @return AccountDTO
     */
    private AccountDTO convertToAccountDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        mapper.map(account, accountDTO);
        return accountDTO;
    }

    /**
     * Convert List Account entity to AccountDTO
     *
     * @param accounts List of entity Account
     * @return AccountDTO
     */
    private List<AccountDTO> convertToListAccountDTO(List<Account> accounts) {
        TypeToken<List<AccountDTO>> typeToken = new TypeToken<List<AccountDTO>>() {
        };
        return mapper.map(accounts, typeToken.getType());
    }

    private Account findAccountById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if (!account.isPresent()) {
            throw new BizException(ResponseEnum.NOT_FOUND, "User not found");
        }
        return account.get();
    }
}
