package com.administracion.administration.services;

import com.administracion.administration.DTOs.AuthDTO;
import com.administracion.administration.DTOs.RecibeEmail;

public interface IAuthService {
    Integer guardarAuth(AuthDTO adto);
    Integer saveAuth(RecibeEmail dto);

    Integer obtenerIdUsuarioXEmail(String email);
}
