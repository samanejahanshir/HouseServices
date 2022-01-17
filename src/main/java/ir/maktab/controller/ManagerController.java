package ir.maktab.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class ManagerController {
    @RequestMapping("/management")
    public String displayManagementPage() {
        return "";
    }
}
