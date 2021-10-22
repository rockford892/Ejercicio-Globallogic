package com.ejercicio.globallogic.repository;

import com.ejercicio.globallogic.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository< Phone, String> {
    
}
