package com.portaone.becomeadeveloper2023test.service.impl;

import com.portaone.becomeadeveloper2023test.service.MainService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MainServiceImpl implements MainService {
    @Override
    public Optional<Character> findFirstUnrepeatedCharacter(String text) {
        text = text.replaceAll("(,|\\.|\\s+|\")"," ");
        String[] words = text.split("\\s+");
        return findFirstEntryWithValueOne(
                Arrays.stream(words)
                        .map(this::findFirstUnique)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting())));
    }

    private Optional<Character> findFirstUnique(String word) {
        return findFirstEntryWithValueOne(word
                .chars()
                .boxed()
                .collect(Collectors.groupingBy(c -> (char) c.intValue(), LinkedHashMap::new, Collectors.counting())));
    }

    private Optional<Character> findFirstEntryWithValueOne(Map<Character, Long> characterCountMap) {
        return characterCountMap.entrySet()
                .stream()
                .filter(pair -> pair.getValue() == 1)
                .findFirst()
                .map(Map.Entry::getKey);
    }
}
