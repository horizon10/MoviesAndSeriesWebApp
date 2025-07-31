package com.horizon.service;

import com.horizon.entity.Role;
import com.horizon.entity.User;
import com.horizon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User update(Long id, User updatedUser) {
        User existing = userRepository.findById(id).orElseThrow();
        existing.setUsername(updatedUser.getUsername());
        existing.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        existing.setEmail(updatedUser.getEmail());
        existing.setImage(updatedUser.getImage());
        return userRepository.save(existing);
    }
    public User updateRole(Long userId, Role newRole) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setRole(newRole);
        return userRepository.save(user);
    }

    public User loginUser(String email, String password) {
        // Veritabanında kullanıcıyı e-posta ile ara
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı."));

        // Şifreyi kontrol et
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Geçersiz şifre.");
        }

        // Eğer doğrulama başarılıysa, kullanıcıyı döndür
        return user;
    }
}
