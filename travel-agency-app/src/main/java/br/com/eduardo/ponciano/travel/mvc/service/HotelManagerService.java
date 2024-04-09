package br.com.eduardo.ponciano.travel.mvc.service;

import br.com.eduardo.ponciano.travel.mvc.model.HotelManager;
import br.com.eduardo.ponciano.travel.mvc.model.User;

public interface HotelManagerService {

    HotelManager findByUser(User user);

}
