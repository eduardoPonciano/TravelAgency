package br.com.eponciano.ms.booking.mvc.service;

import br.com.eponciano.ms.booking.mvc.model.HotelManager;
import br.com.eponciano.ms.booking.mvc.model.User;

public interface HotelManagerService {

    HotelManager findByUser(User user);

}
