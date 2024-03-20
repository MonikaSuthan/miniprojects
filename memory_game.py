import os
import random
from tkinter import Tk, Button, Label, messagebox
from PIL import Image, ImageTk

class GameClass(Tk):
    def __init__(self):
        super().__init__()
        self.geometry("850x600")
        self.config(bg="grey")  # Set background color to grey
        self.number = 0
        self.number_list = []
        self.bottom_image_label = None

    def label(self):
        self.show_button = Button(self, text="Show", command=self.start_show, borderwidth=2, bg="cyan", fg="white", font=("Arial", 16), height=5, width=17)
        self.show_button.place(x=40, y=150)

        self.buttons = []
        positions = [(280, 20), (450, 20), (630, 20), (280, 150), (450, 150), (630, 150), (280, 280), (450, 280), (630, 280)]
        for i in range(9):
            button = Button(self, image="", command=lambda btn=i+1: self.check(btn), borderwidth=2, bg="sky blue", relief="raised", height=125, width=125)
            button.place(x=positions[i][0], y=positions[i][1])
            self.buttons.append(button)
            
            # Add position numbers on the side of each block
            Label(self, text=str(i+1), font=("Arial", 12), fg="black").place(x=positions[i][0]-20, y=positions[i][1]+50)

    def start_show(self):
        if self.number % 2 == 0:  # Show mode
            self.number_list = os.listdir('C:\\Users\\monik\\Downloads\\Images')
            self.photo_images = []
            for i, image_path in enumerate(self.number_list[:9]):
                image = Image.open(os.path.join('C:\\Users\\monik\\Downloads\\Images', image_path))
                image = image.resize((125, 125), Image.ANTIALIAS)
                photo_image = ImageTk.PhotoImage(image)
                self.photo_images.append(photo_image)
                self.buttons[i].config(image=photo_image)
            self.show_button.config(text="Hide", bg="white", fg="black", font=("Arial", 16))
        else:  # Hide mode
            self.display_blank_blocks()
            self.show_button.config(text="Show", bg="white", fg="black", font=("Arial", 16))
            self.display_bottom_image()
        self.number += 1

    def display_bottom_image(self):
        random_image_path = random.choice(self.number_list)
        image = Image.open(os.path.join('C:\\Users\\monik\\Downloads\\Images', random_image_path))
        image = image.resize((125, 125), Image.ANTIALIAS)
        photo_image = ImageTk.PhotoImage(image)
        if self.bottom_image_label:
            self.bottom_image_label.destroy()
        self.bottom_image_label = Label(self, image=photo_image, bg="white")  # Set bottom image block color to white
        self.bottom_image_label.image = photo_image  # Keep reference to avoid garbage collection
        self.bottom_image_label.place(x=280, y=450)

    def display_blank_blocks(self):
        blank_image = Image.new("RGBA", (125, 125), (135, 206, 250))  # Create a blank image with sky blue color
        blank_photo = ImageTk.PhotoImage(blank_image)
        for button in self.buttons:
            button.config(image=blank_photo, text="")  # Display blank blocks with sky blue color

    def check(self, btn_number):
        if self.number % 2 == 0:  # Hide mode
            self.start_show()  # Restart the game
        else:  # Show mode
            image = self.number_list[btn_number - 1]
            image = Image.open(os.path.join('C:\\Users\\monik\\Downloads\\Images', image))
            image = image.resize((125, 125), Image.ANTIALIAS)
            photo_image = ImageTk.PhotoImage(image)
            self.random_character_label = Label(self, image=photo_image, bg="white")  # Set bottom image block color to white
            self.random_character_label.image = photo_image  # Keep reference to avoid garbage collection
            self.random_character_label.place(x=280, y=450)

if __name__ == "__main__":
    game = GameClass()
    game.label()
    game.mainloop()
