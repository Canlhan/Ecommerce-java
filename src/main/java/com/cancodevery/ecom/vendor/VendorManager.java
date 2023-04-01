package com.cancodevery.ecom.vendor;

import com.cancodevery.ecom.Role.Role;
import com.cancodevery.ecom.Role.RoleRepository;
import com.cancodevery.ecom.user.User;
import com.cancodevery.ecom.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorManager implements VendorService{
    private final VendorDao vendorDao;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Autowired
    public VendorManager(VendorDao vendorDao,UserRepository userRepository,RoleRepository roleRepository) {
        this.vendorDao = vendorDao;
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
    }

    @Override
    public List<Vendor> getAll() {
        return vendorDao.findAll();
    }

    @Override
    public Vendor get(int id) {
        return vendorDao.findById(id).get();
    }

    @Override
    public Vendor save(Vendor vendor) {

        User user=new User();
        user.setPassword(new BCryptPasswordEncoder().encode(vendor.getPassword()));
        user.setEmail(vendor.getEmail());

        Role role=roleRepository.findByRoleName("ROLE_VENDOR");


        userRepository.save(user);
        return vendorDao.save(vendor);
    }
}
