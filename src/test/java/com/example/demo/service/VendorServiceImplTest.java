package com.example.demo.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Vendor;
import com.example.demo.entity.VendorBranch;
import com.example.demo.exception.VendorAlreadyExistsException;
import com.example.demo.exception.VendorNotFoundException;
import com.example.demo.repository.VendorRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class VendorServiceImplTest {

    @Mock
    private VendorRepository vendorRepository;

    @InjectMocks
    private VendorServiceImpl vendorServiceImpl;

    @Test
    void testGetAllVendors() {
        Vendor vendor1 = new Vendor();
        Vendor vendor2 = new Vendor();
        List<Vendor> vendors = Arrays.asList(vendor1, vendor2);

        when(vendorRepository.findAll()).thenReturn(vendors);

        List<Vendor> result = vendorServiceImpl.getAllVendors();

        assertAll(() -> assertEquals(2, result.size()), 
                  () -> assertEquals(vendor1, result.get(0)),
                  () -> assertEquals(vendor2, result.get(1)));
    }
    
    @Test
    void testGetVendorById_Found() throws VendorNotFoundException {
        Vendor vendor = new Vendor();
        when(vendorRepository.findById(1)).thenReturn(Optional.of(vendor));

        Vendor result = vendorServiceImpl.getVendorById(1);

        assertEquals(vendor, result);
    }
    
    @Test
    void testGetVendorByName_Found() throws VendorNotFoundException {
        Vendor vendor = new Vendor();

        when(vendorRepository.findByVendorName("Vendor Name")).thenReturn(Optional.of(vendor));

        Vendor result = vendorServiceImpl.getVendorByName("Vendor Name");

        assertEquals(vendor, result);
    } 
    @Test
    void testAddVendor_Success() throws VendorAlreadyExistsException {
        
        Vendor vendor = new Vendor();
        when(vendorRepository.findByContactEmail(vendor.getContactEmail())).thenReturn(Optional.empty());
        when(vendorRepository.save(vendor)).thenReturn(vendor);

        SuccessResponse response = vendorServiceImpl.addVendor(vendor);

        assertEquals("Vendor details added successfully", response.getMessage());
        verify(vendorRepository).save(vendor);
    }
    

    @Test
    void testUpdateVendor_Success() throws VendorNotFoundException {
      
        int vendorId = 1;
        Vendor existingVendor = new Vendor();
        Vendor updatedVendor = new Vendor();
        updatedVendor.setVendorName("New Name");
        updatedVendor.setDescription("New Description");
        updatedVendor.setContactPersonName("New Contact Person");
        updatedVendor.setContactEmail("new.email@example.com");
        updatedVendor.setContactPhone("1234567890");
        updatedVendor.setWebsiteUrl("http://newwebsite.com");
        updatedVendor.setTotalGoldQuantity(100.0);
        updatedVendor.setCurrentGoldPrice(2000.0);

        when(vendorRepository.findById(vendorId)).thenReturn(Optional.of(existingVendor));
        when(vendorRepository.save(existingVendor)).thenReturn(existingVendor);

        SuccessResponse response = vendorServiceImpl.updateVendor(vendorId, updatedVendor);

        assertEquals("Vendor details updated successfully", response.getMessage());
        verify(vendorRepository).save(existingVendor);
    }
    
    @Test
    void testUpdateTotalGoldQuantity_Success() throws VendorNotFoundException {
       
        int vendorId = 1;
        Double newQuantity = 150.0;
        Vendor existingVendor = new Vendor();
 
        existingVendor.setTotalGoldQuantity(100.0); 

        when(vendorRepository.findById(vendorId)).thenReturn(Optional.of(existingVendor));
        when(vendorRepository.save(existingVendor)).thenReturn(existingVendor);
        
        SuccessResponse response = vendorServiceImpl.updateTotalGoldQuantity(vendorId, newQuantity);

        assertEquals("Total gold quantity updated successfully", response.getMessage());
        assertEquals(newQuantity, existingVendor.getTotalGoldQuantity());
        verify(vendorRepository).findById(vendorId);
        verify(vendorRepository).save(existingVendor);
    }
}
    
 
