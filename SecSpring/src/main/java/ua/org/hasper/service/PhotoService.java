package ua.org.hasper.service;

import ua.org.hasper.Entity.Photo;

/**
 * Created by Pavel.Eremenko on 19.01.2017.
 */
public interface PhotoService {
    Photo findById(long id);
    void add(Photo photo);
    void delete(Photo photo);
}
