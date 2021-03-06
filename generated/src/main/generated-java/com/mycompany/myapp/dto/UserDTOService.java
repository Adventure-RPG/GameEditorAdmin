/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Source code: https://github.com/jaxio/celerio/
 * Follow us on twitter: @jaxiosoft
 * This header can be customized in Celerio conf...
 * Template pack-angular:src/main/java/dto/EntityDTOService.java.e.vm
 */
package com.mycompany.myapp.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.domain.User_;
import com.mycompany.myapp.dto.support.PageRequestByExample;
import com.mycompany.myapp.dto.support.PageResponse;
import com.mycompany.myapp.repository.UserRepository;

/**
 * A simple DTO Facility for User.
 */
@Service
public class UserDTOService {

    @Inject
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserDTO findOne(Integer id) {
        return toDTO(userRepository.findOne(id));
    }

    @Transactional(readOnly = true)
    public List<UserDTO> complete(String query, int maxResults) {
        List<User> results = userRepository.complete(query, maxResults);
        return results.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PageResponse<UserDTO> findAll(PageRequestByExample<UserDTO> req) {
        Example<User> example = null;
        User user = toEntity(req.example);

        if (user != null) {
            ExampleMatcher matcher = ExampleMatcher.matching() //
                    .withMatcher(User_.login.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(User_.email.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(User_.firstName.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(User_.lastName.getName(), match -> match.ignoreCase().startsWith());

            example = Example.of(user, matcher);
        }

        Page<User> page;
        if (example != null) {
            page = userRepository.findAll(example, req.toPageable());
        } else {
            page = userRepository.findAll(req.toPageable());
        }

        List<UserDTO> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
    }

    /**
     * Save the passed dto as a new entity or update the corresponding entity if any.
     */
    @Transactional
    public UserDTO save(UserDTO dto) {
        if (dto == null) {
            return null;
        }

        final User user;

        if (dto.isIdSet()) {
            User userTmp = userRepository.findOne(dto.id);
            if (userTmp != null) {
                user = userTmp;
            } else {
                user = new User();
                user.setId(dto.id);
            }
        } else {
            user = new User();
        }

        user.setLogin(dto.login);

        user.setPassword(dto.password);

        user.setEmail(dto.email);

        user.setIsEnabled(dto.isEnabled);

        user.setFirstName(dto.firstName);

        user.setLastName(dto.lastName);

        user.setCreationDate(dto.creationDate);

        user.setCreationAuthor(dto.creationAuthor);

        user.setLastModificationDate(dto.lastModificationDate);

        user.setLastModificationAuthor(dto.lastModificationAuthor);

        user.setVersion(dto.version);

        return toDTO(userRepository.save(user));
    }

    /**
     * Converts the passed user to a DTO.
     */
    public UserDTO toDTO(User user) {
        return toDTO(user, 1);
    }

    /**
     * Converts the passed user to a DTO. The depth is used to control the
     * amount of association you want. It also prevents potential infinite serialization cycles.
     *
     * @param user
     * @param depth the depth of the serialization. A depth equals to 0, means no x-to-one association will be serialized.
     *              A depth equals to 1 means that xToOne associations will be serialized. 2 means, xToOne associations of
     *              xToOne associations will be serialized, etc.
     */
    public UserDTO toDTO(User user, int depth) {
        if (user == null) {
            return null;
        }

        UserDTO dto = new UserDTO();

        dto.id = user.getId();
        dto.login = user.getLogin();
        dto.password = user.getPassword();
        dto.email = user.getEmail();
        dto.isEnabled = user.getIsEnabled();
        dto.firstName = user.getFirstName();
        dto.lastName = user.getLastName();
        dto.creationDate = user.getCreationDate();
        dto.creationAuthor = user.getCreationAuthor();
        dto.lastModificationDate = user.getLastModificationDate();
        dto.lastModificationAuthor = user.getLastModificationAuthor();
        dto.version = user.getVersion();
        if (depth-- > 0) {
        }

        return dto;
    }

    /**
     * Converts the passed dto to a User.
     * Convenient for query by example.
     */
    public User toEntity(UserDTO dto) {
        return toEntity(dto, 1);
    }

    /**
     * Converts the passed dto to a User.
     * Convenient for query by example.
     */
    public User toEntity(UserDTO dto, int depth) {
        if (dto == null) {
            return null;
        }

        User user = new User();

        user.setId(dto.id);
        user.setLogin(dto.login);
        user.setPassword(dto.password);
        user.setEmail(dto.email);
        user.setIsEnabled(dto.isEnabled);
        user.setFirstName(dto.firstName);
        user.setLastName(dto.lastName);
        user.setCreationDate(dto.creationDate);
        user.setCreationAuthor(dto.creationAuthor);
        user.setLastModificationDate(dto.lastModificationDate);
        user.setLastModificationAuthor(dto.lastModificationAuthor);
        user.setVersion(dto.version);
        if (depth-- > 0) {
        }

        return user;
    }
}