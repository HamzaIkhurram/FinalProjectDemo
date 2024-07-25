package com.subwaysimulator.utils;

import com.sun.speech.freetts.*;

public class Announcement {
    private static final String VOICENAME = "kevin16";

    public static void announce(String message) {
        Voice voice;
        VoiceManager vm = VoiceManager.getInstance();
        voice = vm.getVoice(VOICENAME);

        if (voice != null) {
            voice.allocate();
            voice.speak(message);
            voice.deallocate();
        } else {
            System.out.println("Voice not found");
        }
    }

    public static void main(String[] args) {
        announce("Next stop: Mill Creek Station, you can change your train to line blue");
    }
}
