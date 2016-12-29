package com.sample.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sample.service.IAlbumService;

@Controller
public class AlbumController {

	Logger log = LoggerFactory.getLogger(AlbumController.class);

	@Autowired
	IAlbumService albumService;

	@RequestMapping(value = "/albums", method = RequestMethod.GET)
	public String getListAllAlbums(Pageable pageable, Model model) throws Exception {
		model.addAttribute("page", albumService.findAll(pageable));
		return "album";
	}
}
