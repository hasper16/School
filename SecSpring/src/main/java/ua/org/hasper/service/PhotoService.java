package ua.org.hasper.service;

import ua.org.hasper.Entity.Photo;

public interface PhotoService {
    Photo findById(long id);

    void add(Photo photo);

    void delete(Photo photo);
}
