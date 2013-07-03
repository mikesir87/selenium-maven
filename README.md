# Selenium Maven test helper

## Introduction

This repo demonstrates the ability of being able to use a simple <code>AbstractRemoteSeleniumTest</code> class with some Maven configuration to run Selenium test cases on any available Selenium Remote Server.

## Usage

To run the test cases, one only needs to run Maven with the following profile:

<code>mvn -P selenium-remote</code>

### Configuration options

The available configuration options are available at the command-line for Selenium test case execution:

<table>
  <thead><tr><th>Option</th><th>Description</th><th>Options</th><th>Default</th></tr></thead>
  <tbody>
    <tr><td><code>selenium.host</code></td><td>the host that should be used when connecting to the Selenium Remote Server</td><td>Any host name or IP Address</td><td>localhost</td></tr>
    <tr><td><code>selenium.port</code></td><td>the port that should be used when connecting to the Selenium Remote Server</td><td>Any number</td><td>4444</td></tr>
    <tr><td><code>selenium.browsers</code></td><td>the browsers that should be launched during the test case execution. Value is space-limited list of desired browsers</td>
    <td>chrome ie firefox opera safari</td>
    <td>chrome firefox</td></tr>
  </tbody>
</table>

## Examples

To run Selenium test cases using a Selenium Remote Server running on your own local machine:

<code>mvn clean install -P selenium-remote</code>

To run Selenium test cases on a remote server at "seleniumRunner.local" and port 1234:

<code>mvn clean install -P selenium-remote -Dselenium.host=seleniumRunner.local -Dselenium.port=1234</code>

To run Selenium test cases on my local machine, but on Internet Explorer, Chrome, and Firefox:

<code>mvn clean install -P selenium-remote -Dselenium.browsers="ie chrome firefox"</code>