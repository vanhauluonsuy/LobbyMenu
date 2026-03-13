# NeyuLobbyMenu

![Minecraft](https://img.shields.io/badge/Minecraft-1.20+-brightgreen)
![Platform](https://img.shields.io/badge/Platform-Paper%20%7C%20Spigot-blue)
![License](https://img.shields.io/badge/License-Custom-lightgrey)
![Author](https://img.shields.io/badge/Author-vanhauluonsuy-purple)

---

## 🇻🇳 Giới thiệu

**NeyuLobbyMenu** là plugin tạo **menu lobby đơn giản và nhẹ** cho server Minecraft.

Plugin cho phép người chơi mở menu và truy cập nhanh các chức năng như:

* Chuyển xu
* Vào chế độ chơi
* Xem thông tin server

Menu có thể cấu hình dễ dàng bằng **config.yml**.

---

## 🇺🇸 Introduction

**NeyuLobbyMenu** is a lightweight **lobby menu plugin** for Minecraft servers.

Players can open a menu and quickly access server features such as:

* Transfer coins
* Join game modes
* View server information

The menu is fully configurable via **config.yml**.

---

## ✨ Features

* Simple lobby GUI menu
* Fully configurable items
* Command execution from menu
* Item sounds
* Particle effects
* Cooldown support
* Lightweight and optimized

---

## ⚙️ Example Configuration

```yml
items:
  chuyen_xu:
    material: CHEST
    slot: 2
    name: "&6&lChuyển Xu"
    lore:
      - "&7Nhấn để chuyển xu"
    cooldown: 5
    commands:
      - "chuyenxu"
    sound: ENTITY_EXPERIENCE_ORB_PICKUP
    particle: VILLAGER_HAPPY

  play:
    material: TOTEM_OF_UNDYING
    slot: 4
    name: "&a&lPlay"
    lore:
      - "&7Vào chế độ chơi"
    cooldown: 3
    commands:
      - "play"
    sound: UI_BUTTON_CLICK
    particle: CRIT

  info:
    material: BELL
    slot: 6
    name: "&b&lInfo"
    lore:
      - "&7Thông tin server"
    cooldown: 5
    commands:
      - "info"
    sound: BLOCK_BELL_USE
    particle: ENCHANTMENT_TABLE
```

---

## 📦 Requirements

* **Paper / Spigot** `1.20+`
* **Java 17+`

---

## 📥 Installation

1. Download `NeyuLobbyMenu.jar`
2. Put it into your `plugins/` folder
3. Restart the server
4. Edit `plugins/NeyuLobbyMenu/config.yml`

---

## 👨‍💻 Author

Developed by **vanhauluonsuy**

---

## 📜 License

Custom License
Do not redistribute without permission.
