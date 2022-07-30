package com.example.PexChat.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PexChat.Model.MessegesKey;
import com.example.PexChat.Model.Messenges;

@Repository
public interface MessengesRepo extends JpaRepository<Messenges,MessegesKey>{
    
}
