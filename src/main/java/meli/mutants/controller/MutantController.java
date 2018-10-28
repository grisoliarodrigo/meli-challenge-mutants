package meli.mutants.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import meli.mutants.domain.Human;
import meli.mutants.domain.Stats;
import meli.mutants.service.HumanService;
import meli.mutants.util.detector.exceptions.InvalidDNAException;

@RestController
public class MutantController {

	@Autowired
	private HumanService humanService;

	@RequestMapping(value = "/mutant", method = RequestMethod.POST)
	public ResponseEntity<?> isMutant(@RequestBody Human human) {

		try {
			humanService.save(human);
		} catch (InvalidDNAException e) {
			return getBadRequestMessage(e.getMessage());
		}

		if (human.isMutant()) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

	}

	@RequestMapping(value = "/stats")
	public Stats getStats() {
		return humanService.getStats();
	}

	@RequestMapping(value = "/")
	public Map<String, String> APIstatus() {

		Map<String, String> response = new HashMap<String, String>();

		response.put("status", "OK");
		response.put("version", "0.0.3");

		return response;
	}

	private ResponseEntity<?> getBadRequestMessage(String message) {
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", message);
		response.put("status", HttpStatus.BAD_REQUEST.toString());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}