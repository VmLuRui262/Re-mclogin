# Re-McLogin | 重生:Minecraft登录模组
[Fabric](https://fabricmc.net/) mod for [Minecraft: Java Edition](https://www.minecraft.net/) to protect offline mode servers adding `/login` and `/register`. This is a server-side mod, clients don't have to download it.

适用于[Minecraft: Java Edition](https://www.minecraft.net/)的[Fabric](https://fabricmc.net/) Mod。为`online-mode = false`的服务器添加登录验证。

这是个服务器mod，也就是说你不需要在客户端上安装它。

If you like this repositories, please give a star⭐️ .

如果你喜欢这个项目[仓库]，请点个star⭐️ 。

###  Requirement | 前置

- [Fabric-Api](https://minecraft.curseforge.com/projects/fabric/)

## Features | 展望未来
- Add language files | 添加各语言的文件，而不是像现在一样输出两条不同语言的b话。
## Commands | 指令
- /reg `<newPassword>` `<confirmPassword>`
- /l `<password>`
- /passwd `<oldPassword>` `<newPassword>` `<confirmPassword>`
- /dereg `<UserName>`*OP Only, [CL Build](https://github.com/VmLuRui262/Re-mclogin/releases/tag/CL-2.0.0) has added this command.
## Build | 构建
You need to install JDK16.

你需要安装JDK16。

- Windows: `git clone https://github.com/VmLuRui262/Re-mclogin.git && cd .\Re-mclogin && .\gradlew.bat build`

- Linux: `git clone https://github.com/VmLuRui262/Re-mclogin.git && cd ./Re-mclogin && ./gradlew build`
## Thank | 感谢以下同类型项目
Without them there would be no  this repo.

没有他们就没有这个项目。

- [molaeiali](https://github.com/molaeiali) / [mclogin](https://github.com/molaeiali/mclogin)
- [Londiuh](https://github.com/Londiuh) / [login](https://github.com/Londiuh/login)
