# GitHub Setup and Collaboration Guide

## Step 1: Create a Repository on GitHub

1. Go to [github.com](https://github.com) and log in to your account
2. Click the **+** icon in the top right corner and select **New repository**
3. Configure the repository:
   - **Repository name**: `untitled` (or your preferred name)
   - **Description**: `Selenium test automation framework for web application testing`
   - **Visibility**: Choose **Public** (for team collaboration) or **Private** (for restricted access)
   - **DO NOT** initialize with README, .gitignore, or license (we already have these locally)
4. Click **Create repository**

## Step 2: Connect Local Repository to GitHub

After creating the repository on GitHub, you'll see instructions. Run these commands in your local project:

```bash
# Navigate to your project directory
cd "c:\Users\2464139\OneDrive - Cognizant\Desktop\untitled"

# Add GitHub repository as remote
git remote add origin https://github.com/YOUR_USERNAME/untitled.git

# Rename branch to main (GitHub's default)
git branch -M main

# Push initial code to GitHub
git push -u origin main
```

**Replace `YOUR_USERNAME` with your actual GitHub username**

## Step 3: Verify the Push

Go to your GitHub repository page in your browser. You should see:
- All your project files
- The README.md with project information
- The .gitignore configured for Java/Maven projects

## Team Collaboration Workflow

### For Project Lead (You):
1. ✅ Repository is initialized locally
2. ✅ Code is committed and ready
3. 📤 Push to GitHub (see Step 2 above)
4. 🔐 Configure branch protection rules (optional):
   - Go to Settings → Branches → Add rule
   - Require pull request reviews before merging
   - Require status checks to pass

### For Team Members:

#### Clone the Repository
```bash
git clone https://github.com/YOUR_USERNAME/untitled.git
cd untitled
```

#### Working on Features (Individual Branch Approach)
```bash
# Create and switch to a new feature branch
git checkout -b feature/test-login-functionality

# Make changes to your files
# Example: Add test case in src/test/java/testcases/TC_02.java

# Check what changed
git status

# Stage changes
git add .

# Commit with meaningful message
git commit -m "Add TC_02: Login functionality test"

# Push to your feature branch
git push origin feature/test-login-functionality
```

#### Creating a Pull Request (PR)
1. Go to GitHub repository
2. You'll see a notification about your pushed branch
3. Click **Compare & pull request**
4. Add title and description of changes
5. Click **Create pull request**
6. Wait for code review and approval
7. Merge to main branch

## Git Commands Reference

### Daily Commands
```bash
# Check current branch and status
git status

# Create and switch to new branch
git checkout -b feature/your-feature-name

# Stage all changes
git add .

# Commit changes
git commit -m "Descriptive message"

# Push changes
git push origin feature/your-feature-name

# Pull latest changes from main
git pull origin main

# Switch to main branch
git checkout main
```

### Useful Commands
```bash
# View commit history
git log --oneline

# See all branches
git branch -a

# Undo last commit (keep changes)
git reset --soft HEAD~1

# Delete local branch
git branch -d feature/your-feature-name

# Fetch without merging
git fetch origin
```

## Best Practices

✅ **DO:**
- Create a new branch for each feature/test
- Write clear, descriptive commit messages
- Pull latest changes before starting work
- Test locally before pushing
- Keep commits small and focused
- Use meaningful branch names (e.g., `feature/`, `bugfix/`, `test-`)

❌ **DON'T:**
- Commit directly to main/master
- Push untested code
- Use vague commit messages like "fixed stuff"
- Ignore merge conflicts
- Force push to shared branches

## Handling Merge Conflicts

If conflicts occur during merge:
```bash
# Check status to see conflicts
git status

# Resolve conflicts in the files (marked with <<<< ==== >>>>)
# Edit files manually to keep desired code

# After resolving
git add .
git commit -m "Resolve merge conflicts in [file name]"
git push origin feature/your-feature-name
```

## Setting Up Protected Main Branch (Recommended)

1. Go to repository Settings
2. Click Branches in left sidebar
3. Click Add rule under Branch protection rules
4. Enter `main` as branch name
5. Check:
   - ✅ Require a pull request before merging
   - ✅ Require approvals (set to at least 1)
   - ✅ Require branches to be up to date before merging
6. Click Create

This ensures code review before merging to main!

## Troubleshooting

### "fatal: remote origin already exists"
```bash
git remote remove origin
git remote add origin https://github.com/YOUR_USERNAME/untitled.git
```

### "Permission denied" when pushing
- Ensure you're using the correct GitHub credentials
- Use SSH key authentication (recommended)
- Or use personal access token instead of password

### "Your branch is ahead of origin/main"
```bash
git push origin main
```

---

**Next Step**: Push your code to GitHub using the commands from Step 2!
