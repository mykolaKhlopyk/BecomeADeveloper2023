package com.portaone.becomeadeveloper2023test.controller;

import com.portaone.becomeadeveloper2023test.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MainService mainService;

    @GetMapping
    public String getFindCharacterPageWithResult(@RequestParam(value = "result", required = false) String result, Model model) {
        System.out.println(result);
        model.addAttribute("result", result);
        return "findCharacter";
    }

    @PostMapping
    public String solve(@ModelAttribute("textForAnalyze") String textForAnalyze) {
        var result = mainService.findFirstUnrepeatedCharacter(textForAnalyze);
        return result.map(character -> "redirect:?result=" + character).orElse("redirect:?result=all characters are doubled so result is missing");

    }

}
