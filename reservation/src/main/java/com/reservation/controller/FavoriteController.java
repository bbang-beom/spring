package com.reservation.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.reservation.entity.Event;
import com.reservation.entity.Favorite;
import com.reservation.repository.EventRepository;
import com.reservation.repository.FavoriteRepository;

@Controller
public class FavoriteController {

    private final FavoriteRepository favoriteRepository;
    private final EventRepository eventRepository;

    @Autowired
    public FavoriteController(FavoriteRepository favoriteRepository, EventRepository eventRepository) {
        this.favoriteRepository = favoriteRepository;
        this.eventRepository = eventRepository;
    }

    @GetMapping("/my-favorites")
    public String myFavorites(Model model) {
        model.addAttribute("favorites", favoriteRepository.findAll());
        return "my-favorites";
    }

    @PostMapping("/add-to-favorites")
    public String addToFavorites(@RequestParam Long eventId, @RequestParam(required = false) String note, RedirectAttributes redirectAttributes) {
        Event event = eventRepository.findById(eventId).orElseThrow();
        Optional<Favorite> existingFavorite = favoriteRepository.findByEvent(event);
        if (existingFavorite.isPresent()) {
            redirectAttributes.addFlashAttribute("message", "이미 존재하는 목록입니다.");
            return "redirect:/my-reservations";
        }

        Favorite favorite = new Favorite();
        favorite.setEvent(event);
        favorite.setNote(note);
        favoriteRepository.save(favorite);
        
        redirectAttributes.addFlashAttribute("message", "즐겨찾기 추가 완료");
        return "redirect:/my-reservations";
    }

    @PostMapping("/update-favorite/{id}")
    public String updateFavorite(@PathVariable Long id, @RequestParam String note) {
        Favorite favorite = favoriteRepository.findById(id).orElseThrow();
        favorite.setNote(note);
        favoriteRepository.save(favorite);
        return "redirect:/my-favorites";
    }

    @PostMapping("/delete-favorite/{id}")
    public String deleteFavorite(@PathVariable Long id) {
        favoriteRepository.deleteById(id);
        return "redirect:/my-favorites";
    }
    
    @PostMapping("/delete-note/{id}")
    public String deleteFavoriteNote(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Favorite favorite = favoriteRepository.findById(id).orElseThrow();
        favorite.setNote(null);
        favoriteRepository.save(favorite);
        redirectAttributes.addFlashAttribute("message", "메모 삭제 완료");
        return "redirect:/my-favorites";
    }
}
