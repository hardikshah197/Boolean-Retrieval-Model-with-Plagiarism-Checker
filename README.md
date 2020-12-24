# Boolean-Retrival-Model-with-Plagiarism-Checker
### Here is the small java programs to check up the plagiarism of any type, or of any thing along with the boolean retrival functionality

## Basic intructions before use

#### This repository has 4 things: - BRM, Plagiarism-checker, Files, and Documentations.
- Plagiarism Checker basically finds out pass-off content and searched its frequency **Co-oresponding to ~~Syntax~~ Solution file you upload**. It lies in Plagiarism-checker folder.
- Plagiarism Servlet does the same what Plagiarism do, but it would render out result on Frontend. It also lies in Plagiarism-checker folder.
- Searcher Retrival Analysier (**Sequncial Extraction Retrival Model**) does give you full analysied table containing following things:
  1. How many Nodes (Analyzing-files) have plagiarism.
  2. Starting and Ending time for the above task.
  3. Total time difference taken in this process.
- Multi-Threaded Searcher Model (**Paralled Extraction Retrival Model**) does work the same as Searcher Retrival does, but it uses up the concept of threading to boost the time, and do tasks in less time than Searcher Retrival takes, with well defined result analysis.
- Documentations contains 2 things the Algorithm and Report-~~Thyesis~~ Analysis:
  1. Algorithm contains the algorithms of BRM multi-threading(**Paralled Extraction Retrival Model**) and Searcher-Retrival(**Sequncial Extraction Retrival Model**).
  2. A DOCX file which is the documentation of whole BRM model. 
- Files is the rar file which contains some sample text files for testing purpose.

## Installation

Use this plagiarism_checker or BRM component to your plagiarism [click here](https://github.com/hardikshah197/Boolean-Retrival-Model-with-Plagiarism-Checker.git) to download foobar..

```bash
git clone "https://github.com/hardikshah197/Boolean-Retrival-Model-with-Plagiarism-Checker.git"
cd Plagiarism-Checker
java Plagiarism_Checker
cd BRM
java Searcher_Retrival_Analyzier
java Multi-Threaded-Searcher-Model
```

## Usage
### Basic changes you have to done before to run the code for both the models
> - Change `Folder_Path` value with your directory path where all your files lay
> - Change `SearchFile_Path` value with your answered or key valued file's path


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate

## License
[MIT](https://choosealicense.com/licenses/mit/)
