# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a **frontend-only web application** for the Moroccan Regional Education Directorate school management system. It facilitates communication and project tracking between individual schools and the regional education administration.

## Project Architecture

### Two-Interface System:
1. **School Interface** - For school directors/principals to communicate with regional administration
2. **Administrative Interface** - For regional staff to manage schools and communications

## File Structure & Navigation Flow

### School Interface Files:
- `acceuil.html` - **Main dashboard** for school directors with two primary actions
  - Links to: `received_letters.html` and `general_update.html`
- `received_letters.html` - **Letter inbox** showing messages from regional administration
  - Links to: `respond_letter.html` for responding to specific letters
- `respond_letter.html` - **Response form** with structured fields for project updates
- `general_update.html` - **Voluntary reporting** form for school status updates
- `etatgeneral.html` - **General status reporting** (linked from letters or admin requests)
- `suiviprojet.html` - **Project tracking** form for ongoing construction/renovation projects

### Administrative Interface Files:
- `admin_school_dashboard.html` - **Main admin dashboard** showing all schools in grid format
  - Clicking any school card navigates to: `school_detail_page.html`
- `school_detail_page.html` - **Individual school management** with full CRUD operations
  - Features: school data editing, project status toggle, technician assignment
- `letter_management_page.html` - **Letter/communication management** showing sent letters and response rates
  - Links to: `letter_detail_page.html`
- `letter_detail_page.html` - **Detailed letter view** with response tracking and management tools

## Key Technical Characteristics

### Development Stack:
- **Pure HTML/CSS/JavaScript** - No frameworks, build tools, or external dependencies
- **Embedded Styling** - All CSS is within `<style>` tags in each HTML file
- **Embedded Scripts** - All JavaScript is within `<script>` tags in each HTML file
- **Self-contained files** - Each HTML file is completely independent

### Design System:
**Moroccan Government Color Palette:**
- `--primary-red: #C41E3A` (Moroccan flag red)
- `--primary-green: #006233` (Moroccan flag green)
- `--admin-primary: #1565C0` (Administrative blue)
- `--accent-orange: #FF8C00` (Government orange)
- Consistent use of CSS custom properties for theming

### Responsive Design:
- **Mobile-first approach** with CSS Grid and Flexbox
- Breakpoint at `768px` for mobile adaptations
- Touch-friendly interface elements
- Collapsible navigation on mobile

## Core Functionality

### School Director Workflow:
1. **Dashboard** (`acceuil.html`) → Choose action
2. **Receive Letters** → View inbox → Respond with structured forms
3. **Send Updates** → Voluntary status reports or specific project updates

### Administrative Workflow:
1. **School Management** → View all schools → Edit individual school details
2. **Project Tracking** → Assign technicians → Monitor progress
3. **Communication** → Send letters to multiple schools → Track responses

### Advanced Features:
- **Multi-school selection** with bulk operations
- **Search and filtering** across school listings
- **Project status management** with technician assignment (5 predefined technicians)
- **Response rate tracking** with visual progress indicators
- **Export capabilities** (Word, Excel, clipboard)

## Navigation UX Patterns

### School Interface:
- **Simple 2-choice dashboard** (receive vs. send)
- **Linear workflow** for responding to letters
- **Breadcrumb navigation** for context
- **Form-heavy interface** with validation

### Administrative Interface:
- **Data-rich dashboards** with filtering/search
- **Card-based school listings** (clickable for details)
- **Tabbed interfaces** for different views
- **Modal-free design** (uses dedicated pages instead)

## Data Structures

### School Entity:
- Basic info: name, director, contact details, region
- Metrics: student count, class count, staff count
- Project status: boolean toggle with type selection
- Technician assignment: dropdown from 5 predefined names

### Letter/Communication:
- Types: "Rapport de Projet" vs "État Spécifique"
- Status tracking: sent, received, responded
- Response collection with structured forms
- Deadline management

## Development Notes

### Common Patterns:
- **No external dependencies** - everything is vanilla HTML/CSS/JS
- **Consistent color theming** using CSS custom properties
- **Event handling** primarily through inline onclick handlers
- **Form validation** using HTML5 attributes and basic JavaScript
- **Responsive grids** using CSS Grid with auto-fit/auto-fill

### File Naming:
- French names reflecting Moroccan administrative context
- `acceuil.html` = welcome/home page
- `etatgeneral.html` = general status page
- `suiviprojet.html` = project tracking page

### Testing Approach:
- **Browser testing** across modern browsers (no build step required)
- **Direct file opening** or simple HTTP server for development
- **Mobile testing** essential due to responsive design

## Customization Points

### Easy Modifications:
- **Colors**: Update CSS custom properties in `:root` selectors
- **School data**: Modify hardcoded school cards in admin dashboard
- **Technician names**: Update dropdown options in school detail page
- **Form fields**: Add/modify form inputs in various forms

### Extension Opportunities:
- **Backend integration**: All forms ready for API submission
- **Authentication**: Login system can be layered on top
- **File uploads**: Photo/document upload placeholders exist
- **Localization**: Structure supports French/Arabic bilingual implementation