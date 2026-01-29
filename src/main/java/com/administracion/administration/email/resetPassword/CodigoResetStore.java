package com.administracion.administration.email.resetPassword;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class CodigoResetStore {

    private final Map<String, CodigoRecuperacion> store = new ConcurrentHashMap<>();

    public void guardar(String email, CodigoRecuperacion codigo) {
        store.put(email, codigo);
    }

    public CodigoRecuperacion obtener(String email) {
        return store.get(email);
    }

    public void eliminar(String email) {
        store.remove(email);
    }    
    
    public Optional<Map.Entry<String, CodigoRecuperacion>> buscarPorCodigo(String codigo) {
        return store.entrySet().stream()
                .filter(e -> e.getValue().getCodigo().equals(codigo))
                .findFirst();
    }
}
