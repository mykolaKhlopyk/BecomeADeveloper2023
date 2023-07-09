package com.portaone.becomeadeveloper2023test.controller;

import com.portaone.becomeadeveloper2023test.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MainService mainService;

    @GetMapping()
    public String getFirstPage() {
        return "findCharacter";
    }

    @GetMapping("/result")
    public String getFindCharacterPageWithResult(@ModelAttribute("result") String result) {
        return "findCharacter";
    }

    @PostMapping ("/")
    public String solve(@ModelAttribute("textForAnalyze") String textForAnalyze, RedirectAttributes ra) {
        var result = mainService.findFirstUnrepeatedCharacter(textForAnalyze);
        String messageResult;
        if (result.isPresent())
            messageResult = String.valueOf(result.get());
        else
            messageResult = "all characters are doubled so result is missing";
        ra.addFlashAttribute("result", messageResult);

        return "redirect:/result";

    }

}
