package com.monolithic.chromamon.shared.infrastructure.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.time.LocalDateTime;

@ControllerAdvice
@Controller
public class RestErrorHandler {

   @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
   public String handleInternalServerError(HttpServerErrorException.InternalServerError ex, Model model) {
      model.addAttribute("status", 500);
      model.addAttribute("errorTitle", "Service Error");
      model.addAttribute("errorMessage", "The analysis service is temporarily unavailable. Please try again in a few moments.");
      model.addAttribute("errorDetails", "REST API returned: " + ex.getMessage());
      model.addAttribute("timestamp", LocalDateTime.now());

      return "error-500";
   }

   @ExceptionHandler(HttpClientErrorException.NotFound.class)
   public String handleNotFound(HttpClientErrorException.NotFound ex, Model model) {
      model.addAttribute("status", 404);
      model.addAttribute("errorTitle", "Resource Not Found");
      model.addAttribute("errorMessage", "The requested analysis or data could not be found.");
      model.addAttribute("timestamp", LocalDateTime.now());

      return "error-404";
   }

   @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
   public String handleUnauthorized(HttpClientErrorException.Unauthorized ex, Model model) {
      model.addAttribute("status", 401);
      model.addAttribute("errorTitle", "Authentication Required");
      model.addAttribute("errorMessage", "Please log in to access this resource.");
      model.addAttribute("timestamp", LocalDateTime.now());

      return "error";
   }

   @ExceptionHandler(ResourceAccessException.class)
   public String handleConnectionError(ResourceAccessException ex, Model model) {
      model.addAttribute("status", 503);
      model.addAttribute("errorTitle", "Connection Error");
      model.addAttribute("errorMessage", "Unable to connect to the analysis service. Please check your connection and try again.");
      model.addAttribute("errorDetails", "Connection timeout or network error");
      model.addAttribute("timestamp", LocalDateTime.now());

      return "error";
   }

   @ExceptionHandler(Exception.class)
   public String handleGenericError(Exception ex, Model model) {
      model.addAttribute("status", 500);
      model.addAttribute("errorTitle", "Unexpected Error");
      model.addAttribute("errorMessage", "An unexpected error occurred while processing your request.");
      model.addAttribute("errorDetails", ex.getClass().getSimpleName() + ": " + ex.getMessage());
      model.addAttribute("timestamp", LocalDateTime.now());

      return "error";
   }
}