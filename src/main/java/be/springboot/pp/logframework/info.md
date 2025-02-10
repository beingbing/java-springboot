### Requirements
1. Multiple severity levels of logs should be supported.
2. DEBUG, INFO, WARN, ERROR and FATAL are some severity levels.
3. Specific level of log can be sent to multiple destinations of our choice. Ex. - error log can be printed on console, saved in local file-system and should be sent to remote file-system. But info logs should be only printed on console.
4. A log statement should be allowed to be processed by multiple loggers which sends it to different destinations

### Note:
didn't understood the use of chain-of-responsibility design pattern. Above requirements could be fulfilled by observer pattern alone.
