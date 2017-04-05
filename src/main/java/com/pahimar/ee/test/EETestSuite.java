package com.pahimar.ee.test;

import com.pahimar.ee.reference.Files;

import java.io.File;

public class EETestSuite extends EnergyValueTestSuite {

    public EETestSuite() {
        super();
    }

    public EETestSuite build() {
        return this;
    }

    public void save() {
        this.save(new File(Files.globalTestDirectory, "ee-test-suite.json"));
    }
}
