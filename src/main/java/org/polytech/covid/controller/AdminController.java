package org.polytech.covid.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
  @GetMapping("/public/all")
  public String allAccess() {
    return "Public content.";
  }

  @GetMapping("/admin/doctorboard")
  @PreAuthorize("hasRole('DOCTOR')")
  public String userAccess() {
    return "Doctor board.";
  }

  @GetMapping("/admin/adminboard")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    return "Admin Board.";
  }

  @GetMapping("/admin/superadminboard")
  @PreAuthorize("hasRole('SUPERADMIN')")
  public String superAdminAccess() {
    return "Super admin Board.";
  }
}