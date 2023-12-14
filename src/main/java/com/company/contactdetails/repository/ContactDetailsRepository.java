package com.company.contactdetails.repository;

import com.company.contactdetails.model.ContactDetails;
import com.company.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactDetailsRepository extends JpaRepository<ContactDetails,Long> {

    ContactDetails findContactDetailsByUser(User user);
}
