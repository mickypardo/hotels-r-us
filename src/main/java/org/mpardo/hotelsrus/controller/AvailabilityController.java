package org.mpardo.hotelsrus.controller;

import org.mpardo.hotelsrus.service.IAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador de Availability que realiza de intermediario entre el Frontend y
 * el Servicio.
 * 
 * @author micky pardo
 * 
 * @version 1.0
 *
 */
@RestController
@RequestMapping("/availabilities")
public class AvailabilityController {

	private IAvailabilityService availabilityService;

	@Autowired
	public AvailabilityController(IAvailabilityService availabilityService) {

		this.availabilityService = availabilityService;

	}

}
