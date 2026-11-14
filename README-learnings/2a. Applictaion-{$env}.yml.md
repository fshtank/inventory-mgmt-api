The application.yml file is supposed to be environment specific based on the environment after the hyphen:

| Environment | Description |
|---|---|
| -prod | prod |
| -stage | staging environment |
| -qa/test | the |

each environment should have a global e environment variable set.  When the application comes in it will only look at the environment specific application.yml file.

