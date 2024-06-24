package com.example.crud.repository;

import com.example.crud.entities.*;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository <Entities, UUID> {

}
