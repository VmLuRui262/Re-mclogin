# Re-McLogin | 重铸-登录
[Fabric](https://fabricmc.net/) mod for [Minecraft: Java Edition](https://www.minecraft.net/) to protect offline mode servers adding `/login` and `/register`. This is a server-side mod, clients don't have to download it.

适用于[Minecraft: Java Edition](https://www.minecraft.net/)的[Fabric](https://fabricmc.net/) Mod。为`online-mode = false`的服务器添加登录验证。

这是个服务器mod，也就是说你不需要在客户端上安装它。
## Features | 展望未来
- ~~Players are invulnerable until they log in~~
- ~~Players can't interact with the world until they log in~~
- ~~Players can't move until they log in~~
- ~~Players can't chat or use commands (only /login and /register) until they log in~~
- ~~Passwords are saved in `./registered-players.json`~~
- ~~Maybe I'll add more~~
- Add language files | 添加各语言的文件，而不是像现在一样输出两条不同语言的b话。
- `/deregister` command will be added for this Mod | `/deregister`将会被添加。
- Abbreviated commands | 缩写指令 [`/register` -> `/reg` , `/login` -> `/l` , `/password` -> `/passwd` ]
## Commands | 命令
- /register `<newPassword>` `<confirmPassword>`
- /login `<password>`
- /password `<oldPassword>` `<newPassword>` `<confirmPassword>`
