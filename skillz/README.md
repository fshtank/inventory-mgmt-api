# skillz — Claude Code Skills for This Project

This directory holds custom skills (reusable instructions) for Claude Code. To activate them, copy each `.md` file into the appropriate Claude Code config location on your machine.

## Where to Copy Skills

Claude Code looks for skills in these locations (checked in order):

1. **Project-level** (active for this project only):
   ```
   <project-root>/.claude/agents/
   ```
2. **User-level** (active for all projects):
   ```
   ~/.claude/agents/
   ```

So to install a skill, copy the `.md` file from `skillz/` into either:
- `./inventory-mgmt-api/.claude/agents/` — only for this project
- `~/.claude/agents/` — globally for all projects

## Available Skills

### 1. `spring-boot-run.md`
**Purpose**: Launch and run the Spring Boot app to verify changes work in the real application before committing.
**Use when**: You need to start the server, test an endpoint, or confirm a fix works at runtime.
**Copy to**: `~/.claude/agents/spring-boot-run.md`

### 2. `maven-build.md`
**Purpose**: Run Maven commands reliably (compile, package, test) with proper error handling.
**Use when**: Building the project, running tests, or troubleshooting compile errors.
**Copy to**: `~/.claude/agents/maven-build.md`

## How Skills Work

Skills are plain `.md` files that Claude Code loads on demand via the `/skill-name` slash command (e.g., `/spring-boot-run`). Each file contains step-by-step instructions that guide Claude through a repeated workflow. They're stored as `skillz/*.md` here so you can review them before installing.
