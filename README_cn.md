# 重生:Minecraft登录模组
![Re-McLogin_ICON](https://raw.githubusercontent.com/VmLuRui262/Re-mclogin/master/src/main/resources/assets/remclogin/icon.png)

[![LICENSE](https://raw.githubusercontent.com/VmLuRui262/Re-mclogin/master/svg/LICENSE.svg)](https://raw.githubusercontent.com/VmLuRui262/Re-mclogin/master/LICENSE)

[English](https://github.com/VmLuRui262/Re-mclogin/blob/master/README.md) | **中文**

适用于[Minecraft: Java Edition](https://www.minecraft.net/)的[Fabric](https://fabricmc.net/) Mod。为`online-mode = false`的服务器添加登录验证。

这是个服务器mod，也就是说你不需要在客户端上安装它。

如果你喜欢这个项目[仓库]，请点个star⭐️ 。

##  前置

- [Fabric-Api](https://minecraft.curseforge.com/projects/fabric/)

## 展望未来
- 添加各语言的文件，而不是像现在一样输出两条不同语言的b话。
- 为`/dereg`添加UserName TAB自动补全

## Commands | 指令
- /reg `<newPassword>` `<confirmPassword>`
- /l `<password>`
- /passwd `<oldPassword>` `<newPassword>` `<confirmPassword>`
- /dereg `<UserName>`*只允许OP, 在 [CL Build](https://github.com/VmLuRui262/Re-mclogin/releases/tag/CL-2.0.0)  添加了此指令
## 构建
你需要安装JDK16。

- Windows: `git clone https://github.com/VmLuRui262/Re-mclogin.git && cd .\Re-mclogin && .\gradlew.bat build`

- Linux: `git clone https://github.com/VmLuRui262/Re-mclogin.git && cd ./Re-mclogin && ./gradlew build`

## 感谢以下同类型项目
没有他们就没有这个项目。

- [molaeiali](https://github.com/molaeiali) / [mclogin](https://github.com/molaeiali/mclogin)
- [Londiuh](https://github.com/Londiuh) / [login](https://github.com/Londiuh/login)