package com.monolithic.chromamon.shared.infrastructure.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class HomeController {

   @GetMapping("/")
   public String home(Model model) {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      boolean isLoggedIn = auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken);
      model.addAttribute("isLoggedIn", isLoggedIn);
      if (isLoggedIn) {
         model.addAttribute("username", auth.getName());
      }
      return "landing";
   }

   @GetMapping("/login")
   public String login(Model model) {
      model.addAttribute("title", "Login - Chromamon");
      return "login";
   }

   @GetMapping("/dashboard")
   public String dashboard(Model model) {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

      if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof Jwt) {
         Jwt jwt = (Jwt) authentication.getPrincipal();
         String username = jwt.getSubject();
         String firstName = jwt.getClaim("firstName");
         String role = jwt.getClaim("role");

         model.addAttribute("title", "Dashboard - Chromamon");
         model.addAttribute("username", username);
         model.addAttribute("firstName", firstName);
         model.addAttribute("role", role);
      }

      return "dashboards";
   }

   @GetMapping("/analyses")
   public String analyses(Model model) {
      model.addAttribute("title", "Chromatographic Analysis");
      return "analyses";
   }

   @GetMapping("/create-account")
   public String createAccount(Model model) {
      return "create-account";
   }

   @GetMapping("/forgot-password")
   public String forgotPassword(Model model) {
      return "forgot-password";
   }

   @GetMapping("/favicon.ico")
   public String favicon(Model model) {
      return "/static/images/favicon.ico";
   }

   @GetMapping("/transformers")
   public String transformers(Model model) {
      model.addAttribute("title", "Transformers");
      return "transformers";
   }

   @GetMapping("/diagnostics")
   public String diagnostics(Model model) {
      model.addAttribute("title", "Diagnostics");
      return "diagnostics";
   }

   @GetMapping("/reports")
   public String reports(Model model) {
      model.addAttribute("title", "Reports");
      return "reports";
   }

   @GetMapping("/users")
   public String users(Model model) {
      model.addAttribute("title", "User Management");
      return "users";
   }

   @GetMapping("/about")
   public String about(Model model) {
      model.addAttribute("title", "About Chromamon");
      model.addAttribute("version", "0.0.1-SNAPSHOT");
      return "about";
   }
}
