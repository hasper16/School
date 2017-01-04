package ua.org.hasper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.hasper.Entity.MarkStamp;
import ua.org.hasper.repository.MarkStampRepository;
import ua.org.hasper.service.MarkStampService;

/**
 * Created by Pavel.Eremenko on 29.09.2016.
 */
@Service
public class MarkStampServiceImpl implements MarkStampService {
    @Autowired
    MarkStampRepository markStampRepository;

    @Override
    @Transactional
    public void addOrUpdateMarkStamp(MarkStamp markStamp){
        markStampRepository.save(markStamp);
    }
    @Override
    @Transactional
    public void delMarkStamp(MarkStamp markStamp){
        markStampRepository.delete(markStamp);
    }
}
