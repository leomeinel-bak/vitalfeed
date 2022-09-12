<!-- PROJECT SHIELDS -->

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![Quality][quality-shield]][quality-url]

<!-- PROJECT LOGO -->
<!--suppress ALL -->
<br />
<p align="center">
  <a href="https://github.com/LeoMeinel/VitalFeed">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">VitalFeed</h3>

  <p align="center">
    Feed players on Spigot and Paper
    <br />
    <a href="https://github.com/LeoMeinel/VitalFeed"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/LeoMeinel/VitalFeed">View Demo</a>
    ·
    <a href="https://github.com/LeoMeinel/VitalFeed/issues">Report Bug</a>
    ·
    <a href="https://github.com/LeoMeinel/VitalFeed/issues">Request Feature</a>
  </p>

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#description">Description</a></li>
        <li><a href="#features">Features</a></li>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#commands-and-permissions">Commands and Permissions</a></li>
        <li><a href="#configuration - config.yml">Configuration</a></li>
		<li><a href="#configuration - messages.yml">Configuration</a></li>
      </ul>
    </li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->

## About The Project

### Description

VitalFeed is a Plugin that gives players the ability to fill their hunger.

This plugin is perfect for any server wanting to give their players some ease of life.

### Features

- Feed yourself
- Feed others

### Built With

- [Gradle 7](https://docs.gradle.org/7.4/release-notes.html)
- [OpenJDK 17](https://openjdk.java.net/projects/jdk/17/)

<!-- GETTING STARTED -->

## Getting Started

To get the plugin running on your server follow these simple steps.

### Commands and Permissions

1. Permission: `vitalfeed.feed`

- Command: `/feed`
- Description: Feed yourself

2. Permission: `vitalfeed.feed.others`

- Command: `/feed <player>`
- Description: Feed other players

3. Permission: `vitalfeed.cooldown.bypass`

- Description: Bypass cooldown

### Configuration - config.yml

```
# Command cool-down
cooldown:
  enabled: true
  # time in s
  time: 120
```

### Configuration - messages.yml

```
cmd: "&fUsage: &b/feed <player>"
no-perms: "&cYou don't have enough permissions!"
player-only: "&cThis command can only be executed by players!"
not-online: "&cPlayer is not online!"
same-player: "&cYou can't feed yourself! Use &b/feed"
player-fed: "&b%player% &fhas been fed"
fed: "&fYou have been fed"
cooldown-active: "&cYou can't use that command for another &b%time-left% &cseconds!"
```

<!-- ROADMAP -->

## Roadmap

See the [open issues](https://github.com/LeoMeinel/VitalFeed/issues) for a list of proposed features (and known
issues).

<!-- CONTRIBUTING -->

## Contributing

Contributions are what make the open source community such an amazing place to be, learn, inspire, and create. Any
contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<!-- LICENSE -->

## License

Distributed under the GNU General Public License v3.0. See `LICENSE` for more information.

<!-- CONTACT -->

## Contact

Leopold Meinel - [leo@meinel.dev](mailto:leo@meinel.dev) - eMail

Project Link - [VitalFeed](https://github.com/LeoMeinel/VitalFeed) - GitHub

<!-- ACKNOWLEDGEMENTS -->

### Acknowledgements

- [README.md - othneildrew](https://github.com/othneildrew/Best-README-Template)

<!-- MARKDOWN LINKS & IMAGES -->

[contributors-shield]: https://img.shields.io/github/contributors-anon/LeoMeinel/VitalFeed?style=for-the-badge
[contributors-url]: https://github.com/LeoMeinel/VitalFeed/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/LeoMeinel/VitalFeed?label=Forks&style=for-the-badge
[forks-url]: https://github.com/LeoMeinel/VitalFeed/network/members
[stars-shield]: https://img.shields.io/github/stars/LeoMeinel/VitalFeed?style=for-the-badge
[stars-url]: https://github.com/LeoMeinel/VitalFeed/stargazers
[issues-shield]: https://img.shields.io/github/issues/LeoMeinel/VitalFeed?style=for-the-badge
[issues-url]: https://github.com/LeoMeinel/VitalFeed/issues
[license-shield]: https://img.shields.io/github/license/LeoMeinel/VitalFeed?style=for-the-badge
[license-url]: https://github.com/LeoMeinel/VitalFeed/blob/main/LICENSE
[quality-shield]: https://img.shields.io/codefactor/grade/github/LeoMeinel/VitalFeed?style=for-the-badge
[quality-url]: https://www.codefactor.io/repository/github/LeoMeinel/VitalFeed
