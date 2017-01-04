package ua.org.hasper.service;


import ua.org.hasper.Entity.MarkStamp;

/**
 * Created by Pavel.Eremenko on 29.09.2016.
 */
public interface MarkStampService {
    void addOrUpdateMarkStamp(MarkStamp markStamp);
    void delMarkStamp(MarkStamp markStamp);

}
