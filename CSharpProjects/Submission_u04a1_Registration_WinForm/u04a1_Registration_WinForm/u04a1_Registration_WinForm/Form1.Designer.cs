namespace u04a1_Registration_WinForm
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.courseComboBox = new System.Windows.Forms.ComboBox();
            this.selectCoursePromptLabel = new System.Windows.Forms.Label();
            this.registeredCourseList = new System.Windows.Forms.ListView();
            this.registeredCoursesLabel = new System.Windows.Forms.Label();
            this.totalCreditValueLabel = new System.Windows.Forms.Label();
            this.totalCreditLabel = new System.Windows.Forms.Label();
            this.statusMessageText = new System.Windows.Forms.Label();
            this.completeRegistrationButton = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // courseComboBox
            // 
            this.courseComboBox.FormattingEnabled = true;
            this.courseComboBox.Location = new System.Drawing.Point(149, 30);
            this.courseComboBox.Name = "courseComboBox";
            this.courseComboBox.Size = new System.Drawing.Size(121, 21);
            this.courseComboBox.TabIndex = 0;
            this.courseComboBox.SelectedIndexChanged += new System.EventHandler(this.courseComboBox_SelectedIndexChanged);
            // 
            // selectCoursePromptLabel
            // 
            this.selectCoursePromptLabel.AutoSize = true;
            this.selectCoursePromptLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 11.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.selectCoursePromptLabel.Location = new System.Drawing.Point(33, 29);
            this.selectCoursePromptLabel.Name = "selectCoursePromptLabel";
            this.selectCoursePromptLabel.Size = new System.Drawing.Size(110, 18);
            this.selectCoursePromptLabel.TabIndex = 1;
            this.selectCoursePromptLabel.Text = "Select Course: ";
            // 
            // registeredCourseList
            // 
            this.registeredCourseList.Location = new System.Drawing.Point(448, 12);
            this.registeredCourseList.Name = "registeredCourseList";
            this.registeredCourseList.Size = new System.Drawing.Size(295, 97);
            this.registeredCourseList.TabIndex = 2;
            this.registeredCourseList.UseCompatibleStateImageBehavior = false;
            // 
            // registeredCoursesLabel
            // 
            this.registeredCoursesLabel.AutoSize = true;
            this.registeredCoursesLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 11.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.registeredCoursesLabel.Location = new System.Drawing.Point(294, 30);
            this.registeredCoursesLabel.Name = "registeredCoursesLabel";
            this.registeredCoursesLabel.Size = new System.Drawing.Size(148, 18);
            this.registeredCoursesLabel.TabIndex = 3;
            this.registeredCoursesLabel.Text = "Registered Courses: ";
            // 
            // totalCreditValueLabel
            // 
            this.totalCreditValueLabel.AutoSize = true;
            this.totalCreditValueLabel.Location = new System.Drawing.Point(558, 128);
            this.totalCreditValueLabel.Name = "totalCreditValueLabel";
            this.totalCreditValueLabel.Size = new System.Drawing.Size(13, 13);
            this.totalCreditValueLabel.TabIndex = 4;
            this.totalCreditValueLabel.Text = "0";
            // 
            // totalCreditLabel
            // 
            this.totalCreditLabel.AutoSize = true;
            this.totalCreditLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 11.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.totalCreditLabel.Location = new System.Drawing.Point(445, 127);
            this.totalCreditLabel.Name = "totalCreditLabel";
            this.totalCreditLabel.Size = new System.Drawing.Size(96, 18);
            this.totalCreditLabel.TabIndex = 5;
            this.totalCreditLabel.Text = "Total Credits:";
            // 
            // statusMessageText
            // 
            this.statusMessageText.AutoSize = true;
            this.statusMessageText.Location = new System.Drawing.Point(36, 226);
            this.statusMessageText.Name = "statusMessageText";
            this.statusMessageText.Size = new System.Drawing.Size(155, 13);
            this.statusMessageText.TabIndex = 6;
            this.statusMessageText.Text = "Select a course from list above.";
            // 
            // completeRegistrationButton
            // 
            this.completeRegistrationButton.Location = new System.Drawing.Point(448, 164);
            this.completeRegistrationButton.Name = "completeRegistrationButton";
            this.completeRegistrationButton.Size = new System.Drawing.Size(128, 23);
            this.completeRegistrationButton.TabIndex = 7;
            this.completeRegistrationButton.Text = "Complete Registration";
            this.completeRegistrationButton.UseVisualStyleBackColor = true;
            this.completeRegistrationButton.Click += new System.EventHandler(this.completeRegistrationButton_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 309);
            this.Controls.Add(this.completeRegistrationButton);
            this.Controls.Add(this.statusMessageText);
            this.Controls.Add(this.totalCreditLabel);
            this.Controls.Add(this.totalCreditValueLabel);
            this.Controls.Add(this.registeredCoursesLabel);
            this.Controls.Add(this.registeredCourseList);
            this.Controls.Add(this.selectCoursePromptLabel);
            this.Controls.Add(this.courseComboBox);
            this.Name = "Form1";
            this.Text = "IT Course Registration";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ComboBox courseComboBox;
        private System.Windows.Forms.Label selectCoursePromptLabel;
        private System.Windows.Forms.ListView registeredCourseList;
        private System.Windows.Forms.Label registeredCoursesLabel;
        private System.Windows.Forms.Label totalCreditValueLabel;
        private System.Windows.Forms.Label totalCreditLabel;
        private System.Windows.Forms.Label statusMessageText;
        private System.Windows.Forms.Button completeRegistrationButton;
    }
}

