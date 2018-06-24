package com.assia.service;

import com.assia.domain.user.PagingObject;
import com.assia.domain.user.User;
import com.assia.domain.user.User_;
import com.assia.model.user.UserForm;
import com.assia.model.user.UserModel;
import com.assia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.Predicate;
import java.util.stream.Collectors;

import static com.assia.domain.user.User_.address;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //    @PreAuthorize("hasRole(ADMIN)")
    public PagingObject<UserModel> getAllUsers(Pageable pageable, String username, String fullName) {
        if (pageable.getPageSize() > 500) throw new RuntimeException("Page size too big");
        PagingObject<UserModel> rs = new PagingObject<>();
        Page<User> userPage;
        if (StringUtils.hasText(username) || StringUtils.hasText(fullName)) {
            userPage = userRepository.findAll((root, query, cb) -> {
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.hasText(username)) {
                    predicates.add(cb.like(root.get(User_.username), "%" + username + "%"));
                }
                if (StringUtils.hasText(fullName)) {
                    predicates.add(cb.like(root.get(address), "%" + address + "%"));
                }
                return cb.or(predicates.toArray(new Predicate[predicates.size()]));
            }, pageable);
        } else {
            userPage = userRepository.findAll(pageable);
        }
        rs.setTotal(userPage.getTotalElements());
        rs.setTotalPage(userPage.getTotalPages());
        rs.setData(userPage.getContent().stream()
                .map(com.assia.domain.user.User::toUser).collect(Collectors.toList()));

        return rs;
    }

    public Optional<com.assia.domain.user.User> getById(BigInteger id) {
        return this.userRepository.getById(id);
    }

    public void delete(BigInteger id) {
        getById(id).ifPresent(this.userRepository::delete);
    }

    public Optional<com.assia.domain.user.User> update(BigInteger id, UserForm userForm) {
        return getById(id).map(user -> {
            user.setUsername(userForm.getUsername());
            user.setPassword(encoder.encode(userForm.getPassword()));
            user.setConfirmPassword(encoder.encode(userForm.getConfirmPassword()));
            user.setEmail(userForm.getEmail());
            user.setAddress(userForm.getAddress());
            user.setFullName(userForm.getFullName());
            user.setPhone(userForm.getPhone());
            user.setRole(userForm.getRole());
            return this.userRepository.save(user);
        });
    }

    public com.assia.domain.user.User create(UserForm userForm) {

        com.assia.domain.user.User user = new com.assia.domain.user.User();
        user.setUsername(userForm.getUsername());
        user.setPassword(encoder.encode(userForm.getPassword()));
        user.setConfirmPassword(encoder.encode(userForm.getConfirmPassword()));
        user.setEmail(userForm.getEmail());
        user.setAddress(userForm.getAddress());
        user.setPhone(userForm.getPhone());
        user.setSex(userForm.getSex());
        user.setFullName(userForm.getFullName());
        user.setRole(userForm.getRole());

        return this.userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.assia.domain.user.User> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return user
                .map(CustomUserDetails::new).get();
    }
}
