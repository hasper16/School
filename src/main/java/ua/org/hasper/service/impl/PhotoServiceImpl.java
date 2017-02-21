package ua.org.hasper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.hasper.Entity.Photo;
import ua.org.hasper.repository.PhotoRepository;
import ua.org.hasper.service.PhotoService;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    @Transactional
    public Photo findById(long id) {
        return photoRepository.findById(id);
    }

    @Override
    @Transactional
    public void add(Photo photo) {
        photoRepository.saveAndFlush(photo);
    }

    @Override
    @Transactional
    public void delete(Photo photo) {
        photoRepository.delete(photo);
    }
}
