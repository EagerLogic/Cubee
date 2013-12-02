Cubee is an UI library based on GWT. The main goal of this project is to create a UI library which can be used to easily create beutifull, animated web applications in a desktop (JavaFX) like environment.

**Main Features**
- Data binding
- Timeline animations
- Redesigned layout system
- Pure Java CSS free styleing
- Easy Custom Component creation

# Data Binding
Using the Property API you can use databinding very similar to JavaFX. You can bind properties to eachother and you can also bind properties to expressions to implement harder logic.

# Timeline animations
Using Cubee you can easily create beautifull animated components and webpages. In cubee, every property is animateable. And every every style and layout stuffs of a component is a property so you can animate nearly anithing (position, background color, opacity, border size and color, and many more).

# Redesigned layout system
Cubee's layout system is redesigned from the ground to eliminate tipical html misunderstanding and cross browser incompatibility. In Cubee, every component is using CSS: position: absolute. This doesn't mean that you need to position everything width absolute coordinates. The most common layout containers like horizontal and vertical layout (HBox, VBox) are all in your hand, but you can easily and safely use absolute position and overlapping elements inside a component.

Vertical align and cool overlapping elements isn't a problem anymore using Cubee.

# Pure Java CSS free styleing
CSS is very good for lot's of things. But Cubee decided not to use CSS for styling stuffs. The reason is that CSS isn't type-safe, and there is too much differences between the browser, interpreting a CSS.

Cubee uses just pure type-safe Java code for styling stuffs. This gives a little overhead on the development, but in the otherside, you always know what you can do whith a component, and what do you need to type to get it work. You will get compile time errors if you went wrong.

In the near feature a type-safe Java styling API will be added to get all the good fetures of CSS and many more. But until this you can use inline Java styling like this:

    label.fontSizeProperty().set(18);
    label.foreColorProperty().set(Color.getArgbColor(0x6000c0ff));
    
    LinearGradient lg = new LinearGradient(-45, 
        new ColorStop(0.0, Color.RED), 
        new ColorStop(0.5, Color.GREEN), 
        new ColorStop(1.0, Color.BLUE));
    panel.backgroundProperty().set(lg);

# Easy Custom Component creation
One of the biggest advantage of Cubee is that you can create reusable Custom Components with custom properties and events. Lets create your cutting edge component and impress the world with it.


