package com.monolithic.chromamon.shared.infrastructure.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
public class CustomErrorController implements ErrorController {

   @RequestMapping("/error")
   public String handleError(HttpServletRequest request, Model model) {
      Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
      Object message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
      Object exception = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
      String path = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);

      model.addAttribute("timestamp", LocalDateTime.now());
      model.addAttribute("path", path);

      if (status != null) {
         int statusCode = Integer.parseInt(status.toString());
         model.addAttribute("status", statusCode);

         switch (statusCode) {
            case 404:
               model.addAttribute("errorTitle", "Page Not Found");
               model.addAttribute("errorMessage", "The page you're looking for doesn't exist or has been moved.");
               return "error-404";

            case 403:
               model.addAttribute("errorTitle", "Access Forbidden");
               model.addAttribute("errorMessage", "You don't have permission to access this resource.");
               break;

            case 500:
               model.addAttribute("errorTitle", "Internal Server Error");
               model.addAttribute("errorMessage", "We're experiencing technical difficulties. Our team has been notified and is working to resolve the issue.");
               if (exception != null) {
                  model.addAttribute("errorDetails", "Exception: " + exception.getClass().getSimpleName());
               }
               return "error-500";

            case 503:
               model.addAttribute("errorTitle", "Service Unavailable");
               model.addAttribute("errorMessage", "The service is temporarily unavailable. Please try again later.");
               break;

            default:
               model.addAttribute("errorTitle", "Error " + statusCode);
               model.addAttribute("errorMessage", "An unexpected error occurred. Please try again.");
         }
      } else {
         model.addAttribute("errorTitle", "Unknown Error");
         model.addAttribute("errorMessage", "An unknown error occurred. Please try again.");
      }

      if (message != null) {
         model.addAttribute("errorDetails", message.toString());
      }

      return "error"; // Template genérico
   }
}