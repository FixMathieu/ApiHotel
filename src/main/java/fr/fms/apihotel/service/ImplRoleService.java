package fr.fms.apihotel.service;

import fr.fms.apihotel.dao.RoleRepository;
import fr.fms.apihotel.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImplRoleService implements IService<Role> {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> getAll() {
        return null;
    }

    @Override
    public Optional<Role> getOneById(long id) {
        return Optional.empty();
    }

    @Override
    public Role save(Role obj) {
        return roleRepository.save(obj);
    }

    @Override
    public void delete(long id) {

    }
}

