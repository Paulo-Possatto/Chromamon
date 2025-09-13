package com.monolithic.chromamon.login.domain.port;

public interface AuditLoginService {

   void logUserCreation(String info);

   void logGetAllUsers();

   void logGetUserCodeById(String info);

   void logUserUpdate(String info);

   void logUserDelete(String info);
}
