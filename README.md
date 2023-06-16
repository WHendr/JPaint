# Jpaint

5 design patterns I used throughout this project are: 
### The observer pattern 
- ShapeList (ISubject) , SelectedList (ISubject), PaintCanvas(IObserver) 
### abstract factory pattern 
- ShapeFactory (AbstractShapeFactory)
### Singleton pattern 
- MasterList
### Prototype pattern 
- Shape (IPrototype) 
### Adapter pattern 
- ColorAdapter (IAdapter), SaveAdapter (IAdapter)

## Jpaint an "MS Paint"-like application

## It currently has the following features:

- Pick a shape
  - Ellipse
  - Triangle
  - Rectangle
  - Pentagon
  - Hexagon
  - Heptagon
  - Octagon
  - Star
  - PacMan
- Pick a primary color
- Pick a secondary color
- Select shading type (outline only, filled-in, outline and filled-in)
- Click and drag to draw a shape
- Undo last action
- Redo last action
- Click and drag to select shapes
- Click and drag to move selected shapes
- Copy selected shapes
- Paste copied shapes
- Delete selected shapes
- Selected shapes have dashed outline
- Group selected shapes
- Ungroup selected shapes

- Save and Load Files(Run into a few bugs running this with shapes not loading but mostly works)
  - SaveHistory(model.persistence)
  - SaveFile(model.persistence)
  - ISaveDialogProvider(model.interfaces)
  - SaveDialogProvider(model.dialog)
  - LoadDialogProvider(model.dialog)
  - ChooseSaveDialog(model.dialog)
  - Save(model)

## Implementations That could use some work:
- Saving/Loading
  - I saw this thing that said that Call of Duty's killcam's aren't actual videos but instead perfect reanactments of every button press. So, I wanted to give it a try.
  - So essentially what this does is it "Saves" every command that is currently being used by CommandHistory then puts it into a "SaveFile" by pressing "Save". Which you can select by pressing "Load" which then makes the app clear all of the CommandHistory and then put all of the Commands in the "SaveFile" into CommandHistory thus recreating the "Save"
  - can be buggy with groups and other certain niche instances
- Pick a shape (located in design.shapes.additionalShapes) (These are hideous)
  - Pentagon
  - Hexagon
  - Heptagon
  - Octagon
  - Star
  - PacMan

