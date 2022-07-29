import { Router } from "express";
import { servicesController } from "./app/controllers/ServicesController";
import { worksController } from "./app/controllers/WorksController";

const router: Router = Router()

// Service Controller
router.get("/", servicesController.status);


// Works Controller
router.get("/obras", worksController.createWork);

router.get("/obras/", worksController.getAllWorks);

router.get("/obras/:id", worksController.getWork);

router.get("/obras/:id", worksController.updateWork);

router.get("/obras/:id", worksController.deleteWork);

export { router };
