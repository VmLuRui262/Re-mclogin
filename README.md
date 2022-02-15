# Re-McLogin | 重铸-登录
Fabric mod for Minecraft: Java Edition to protect offline mode servers adding /login and /register. This is a server-side mod, clients don't have to download it.

## Features | 展望未来
---
- Players are invulnerable until they log in
- Players can't interact with the world until they log in
- Players can't move until they log in
- Players can't chat or use commands (only /login and /register) until they log in
- Passwords are saved in `./registered-players.json`
- Maybe I'll add more
----

## Commands | 命令
- /register `<newPassword>` `<confirmPassword>`
- /login `<password>`
- /password `<oldPassword>` `<newPassword>` `<confirmPassword>`
