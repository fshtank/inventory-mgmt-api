# Claude-Code ASK:
 can you see the file SPEC.md?  I want to you to read that file and build out this application according to this documentation.  use "com.rdllc"
    as the root package.  Refactor the existing package and its child classes into "com.fshtank" and keep them separate from the new project code.
  Update CLAUDE.md appropriately.
  I am new to Claude Code. Create a sub-folder "skillz" and write any skills I need Claude Code to understand and tell me where to copy them

  [I asked Brave Leo for a good project to start](https://search.brave.com/ask?q=What+is+a+good+practice+project+with+Java+JDK25%2C+Spring+Boot%2C+PostgresDB+project+I+can+deploy+as+an+...&conversation=09c5389907d887db09ac1119afe51eb07171)

  3:11 pm 
  Luggable-home : Qwen3.6-35B


  ---- 

  ---

What was done

Application built (com.rdllc.inventory) — per SPEC.md

All 29 files match the spec exactly:

┌───────────────────┬─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐
│       Layer       │                                                                            Files                                                                            │
├───────────────────┼─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤
│ Application entry │ InventoryApplication.java                                                                                                                                   │
├───────────────────┼─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤
│ Entities          │ User.java, Product.java                                                                                                                                     │
├───────────────────┼─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤
│ Repositories      │ UserRepository.java, ProductRepository.java (with aggregate queries)                                                                                        │
├───────────────────┼─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤
│ DTOs              │ 4 request DTOs (RegisterRequest, LoginRequest, ProductCreateRequest, ProductUpdateRequest) + 3 response DTOs (AuthResponse, ProductResponse, StatsResponse) │
├───────────────────┼─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤
│ Services          │ AuthService, ProductService, AdminService                                                                                                                   │
├───────────────────┼─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤
│ Security          │ JwtTokenProvider, JwtAuthenticationFilter, CustomUserDetailsService                                                                                         │
├───────────────────┼─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤
│ Config            │ SecurityConfig (Spring Security 7.1, CSRF disabled, stateless JWT), OpenApiConfig (Swagger UI)                                                              │
├───────────────────┼─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤
│ Controllers       │ AuthController, ProductController, AdminController                                                                                                          │
├───────────────────┼─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤
│ Exceptions        │ GlobalExceptionHandler, ResourceNotFoundException                                                                                                           │
├───────────────────┼─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤
│ Resources         │ application.yml (dev), application-prod.yml (prod with env vars)                                                                                            │
└───────────────────┴─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘

CLAUDE.md

Created at project root — tells Claude Code about the packages, tech stack, Spring Boot 4.x gotchas, endpoints, and build commands.

com.fshtank package

Directory created (src/main/java/com/fshtank/) but empty — there was no existing Java code in this repo to refactor. Drop legacy classes there when ready.

skillz/ directory

Created with 3 files:

┌────────────────────┬──────────────────────────────────────────────────┬─────────────────────────────────────────────┐
│        File        │                     Purpose                      │                Install path                 │
├────────────────────┼──────────────────────────────────────────────────┼─────────────────────────────────────────────┤
│ README.md          │ Explains what skills are and how to install them │ —                                           │
├────────────────────┼──────────────────────────────────────────────────┼─────────────────────────────────────────────┤
│ spring-boot-run.md │ Launch server + verify endpoints manually        │ Copy to ~/.claude/agents/spring-boot-run.md │
├────────────────────┼──────────────────────────────────────────────────┼─────────────────────────────────────────────┤
│ maven-build.md     │ Build, test, package commands                    │ Copy to ~/.claude/agents/maven-build.md     │
└────────────────────┴──────────────────────────────────────────────────┴─────────────────────────────────────────────┘

Other files

- .gitignore — excludes target/, IDE files, OS artifacts
- pom.xml updated with springdoc-openapi-starter-webmvc-ui dependency (for Swagger UI)

---

Where to copy the skills

To install them, copy into one of these locations:

# Option A: User-wide (recommended — use for all projects)
cp skillz/spring-boot-run.md ~/.claude/agents/
cp skillz/maven-build.md ~/.claude/agents/

# Option B: Project-only
mkdir -p .claude/agents
cp skillz/spring-boot-run.md .claude/agents/
cp skillz/maven-build.md .claude/agents/

Once installed, you can invoke them as /spring-boot-run or /maven-build.

---

Recommendations for getting started with Claude Code

1. ! <command> prefix runs shell commands directly in your terminal (e.g., ! ./mvnw spring-boot:run). The output appears right here in the conversation.
2. /config opens settings UI — use it to configure permissions, env vars, or hook automation.
3. /remember saves facts to persistent memory — e.g., "Always use BCrypt for passwords" gets remembered across sessions.
4. .claude/settings.local.json — store secrets-like values (like your JWT_SECRET) here; it's gitignored and auto-loaded.
5. No Maven/Java installed? You'll need at minimum Java 25 (Amazon Corretto) to compile. Run java -version first. The project uses a Maven wrapper pattern (./mvnw) if you add one, but currently relies on system Maven.
6. PostgreSQL setup: Before running, create the database:
createdb inventory  # if not already created
7. Spring Boot 4.x caveat: The spec targets Spring Boot 4.1.2 / Spring Security 7.1 which are cutting-edge. Verify the springdoc-openapi version (2.8.9) is compatible at time of build — this dependency wasn't in SPEC.md but was necessary for the Swagger UI config.

---

Total files created: 30 across the application, configuration, skills, and project context. Nothing to refactor into com.fshtank since there was no existing code — that package is staged and ready when you have legacy code to move.

✻ Worked for 16m 17s · done 3:28 PM

※ recap: The goal was building a Java Spring Boot inventory management API per SPEC.md under com.rdllc while reserving com.fshtank for legacy code. You should now install the skills by copying them from skillz into your Claude Code agents directory. (disable recaps in /config)