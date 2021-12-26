import { Injectable } from "@angular/core";
import { ToastrService } from "ngx-toastr";

export enum StatusToast {
    SUCCESS = 'success',
    ERROR = 'error',
    INFO = 'info',
    WARNING = 'warning',
}

@Injectable({
    providedIn: 'root',
})

export class ToastServiceCodex {
    constructor(
        private toastr: ToastrService
    ) {}

    showToast(mess: string, status: string) {
        if (status === StatusToast.SUCCESS) {
            this.toastr.success(mess,'Thành công', {
                closeButton: true,
                timeOut: 3000,
                extendedTimeOut: 3000
            });
        } else if (status === StatusToast.ERROR) {
            this.toastr.error(mess, 'Thất bại', {
                closeButton: true,
                timeOut: 3000,
                extendedTimeOut: 3000
            })
        }
    }
}