
---
### **Requirements:**

* Docker
* JDK 15+
* Spring Boot Framework

---
### **Setup:**
1. Download & Install requirement
2. Clone this repository
3. cd to project root folder in command line.
4. `sudo systemctl start docker`
5. `docker volume create --name=postgres_wm_data`
6. `docker-compose up`

### **Restart Docker-compose**
1. `docker volume rm postgres_wm_data`
2. `docker-compose down`

### **Remove all Images, Containers, Volumes, and Networks**
`docker system prune -a`
